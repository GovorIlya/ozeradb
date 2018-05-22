import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { RatingMethod } from './rating-method.model';
import { RatingMethodPopupService } from './rating-method-popup.service';
import { RatingMethodService } from './rating-method.service';

@Component({
    selector: 'jhi-rating-method-dialog',
    templateUrl: './rating-method-dialog.component.html'
})
export class RatingMethodDialogComponent implements OnInit {

    ratingMethod: RatingMethod;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private ratingMethodService: RatingMethodService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.ratingMethod.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ratingMethodService.update(this.ratingMethod));
        } else {
            this.subscribeToSaveResponse(
                this.ratingMethodService.create(this.ratingMethod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<RatingMethod>>) {
        result.subscribe((res: HttpResponse<RatingMethod>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: RatingMethod) {
        this.eventManager.broadcast({ name: 'ratingMethodListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-rating-method-popup',
    template: ''
})
export class RatingMethodPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ratingMethodPopupService: RatingMethodPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ratingMethodPopupService
                    .open(RatingMethodDialogComponent as Component, params['id']);
            } else {
                this.ratingMethodPopupService
                    .open(RatingMethodDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
